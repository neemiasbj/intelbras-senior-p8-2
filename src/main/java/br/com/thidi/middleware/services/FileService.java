
package br.com.thidi.middleware.services;


 import br.com.thidi.middleware.resource.CLogger;
 import java.io.IOException;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import java.nio.file.attribute.FileAttribute;
 import java.util.Arrays;


 public class FileService
 {
	 public static void checkOrCreateFile(Path filePath, String fileContent) {
		 if (!Files.exists(filePath, new java.nio.file.LinkOption[0]))
			 try {
				 Files.createFile(filePath, (FileAttribute<?>[]) new FileAttribute[0]);
				 Files.write(filePath, Arrays.asList((CharSequence[]) new String[] { fileContent }),
						new java.nio.file.OpenOption[0]);
				 CLogger.logFileInfo("File Service", fileContent);
				 } catch (IOException e) {
				 CLogger.logFileInfo("File Service", "Error creating file created at: " + filePath);
				 }
		 }
	 }

