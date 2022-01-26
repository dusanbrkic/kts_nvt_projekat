package gradjanibrzogbroda.backend.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

import gradjanibrzogbroda.backend.exceptions.StorageException;
import gradjanibrzogbroda.backend.exceptions.StorageFileNotFoundException;
import gradjanibrzogbroda.backend.config.StorageProperties;

public class StorageUtil {
	public static void store(String fileBase64, String path, String filename) {

		try {
			String parsedFileBase64 = fileBase64;

			if (fileBase64.isEmpty()){ return; }

			String[] splittedFile = fileBase64.split(",");
			if(splittedFile.length!=1){
				parsedFileBase64 = splittedFile[1];
			}

			byte[] slikaDecoded = Base64.getDecoder().decode(parsedFileBase64);

			if (slikaDecoded == null) {
				throw new StorageException("Failed to store empty file.");
			}

			File file = new File(path + filename);
			file.createNewFile();
			try (InputStream inputStream = new ByteArrayInputStream(slikaDecoded)) {
				Files.copy(inputStream, Path.of(file.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}

	public static String loadAsString(String path, String filename) {
		try {
			File file = new File(path + filename);
			if (file.exists() || file.isFile()) {
				return Base64.getEncoder().withoutPadding().encodeToString(new FileInputStream(file).readAllBytes());
			}
			else {
				throw new StorageFileNotFoundException(
						"Could not read file: " + filename);

			}
		} catch (StorageFileNotFoundException | IOException e) {
			return "";
		}
	}
}
