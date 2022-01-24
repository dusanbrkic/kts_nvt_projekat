package gradjanibrzogbroda.backend.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import gradjanibrzogbroda.backend.exceptions.StorageException;
import gradjanibrzogbroda.backend.exceptions.StorageFileNotFoundException;
import gradjanibrzogbroda.backend.config.StorageProperties;

@Service
public class StorageService {
	public void store(String fileBase64, String filename) {
		try {
			if (fileBase64.isEmpty()){ return; }

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
			File file = new File(StorageProperties.getResourceFolderRelativePath() + System.getProperty("file.separator") +
					StorageProperties.getZaposleniProfilePicsLocation() + System.getProperty("file.separator") + filename);
			if (file.exists() || file.isFile()) {
				return Base64.getEncoder().withoutPadding().encodeToString(new FileInputStream(file).readAllBytes());
			}
			else {
				throw new StorageFileNotFoundException(
						"Could not read file: " + filename);

			}
		} catch (StorageFileNotFoundException | IOException e) {
			e.printStackTrace();
			return "";
		}
	}
}
