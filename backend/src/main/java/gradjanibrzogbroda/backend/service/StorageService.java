package gradjanibrzogbroda.backend.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import gradjanibrzogbroda.backend.exceptions.StorageException;
import gradjanibrzogbroda.backend.exceptions.StorageFileNotFoundException;
import gradjanibrzogbroda.backend.util.StorageProperties;

@Service
public class StorageService {
	public void store(String fileBase64, String filename) {
		try {
			byte[] slikaDecoded =  Base64.getDecoder().decode(fileBase64.split(",")[1]);

			if (slikaDecoded == null) {
				throw new StorageException("Failed to store empty file.");
			}

			File file = new File(StorageProperties.getResourceFolderRelativePath() + System.getProperty("file.separator") +
					StorageProperties.getZaposleniProfilePicsLocation() + System.getProperty("file.separator") + filename);
			file.createNewFile();
			try (InputStream inputStream = new ByteArrayInputStream(slikaDecoded)) {
				Files.copy(inputStream, Path.of(file.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}

	public String loadAsString(String filename) {
		try {
			Resource resource = new ClassPathResource(StorageProperties.getZaposleniProfilePicsLocation() + System.getProperty("file.separator") + filename);
			if (resource.exists() || resource.isReadable()) {
				return Base64.getEncoder().withoutPadding().encodeToString(resource.getInputStream().readAllBytes());
			}
			else {
				throw new StorageFileNotFoundException(
						"Could not read file: " + filename);

			}
		} catch (StorageFileNotFoundException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
