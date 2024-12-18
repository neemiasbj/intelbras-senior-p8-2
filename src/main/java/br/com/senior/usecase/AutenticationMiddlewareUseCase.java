package br.com.senior.usecase;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.response.AuthenticationSuccessfull;
import br.com.senior.service.message.HeaderMessageFactory;
import br.com.senior.service.message.HeaderMessageFactoryImpl;
import br.com.util.LogUtils;
import br.com.util.Utils;

@Component
public class AutenticationMiddlewareUseCase {
    
    @Value("${senior.driver}")
    private String driver;
    
    @Value("${senior.certificate}")
    private String certificate;
    
    private static final Logger logger = LoggerFactory.getLogger("AutenticationMiddlewareUseCase");
    
    public short getDriverIdAsShort() {
        logger.info("Driver Id: {}", driver);
        try {
            return Short.parseShort(driver);
        } catch (Exception e) {
            throw new IllegalArgumentException("Driver ID cannot be converted to short: " + driver, e);
        }
    }
    
    public byte[] getCertificateAsByteArray() {
        logger.info("Certificate: {}", certificate);
        
        try {
            ClassPathResource classPathResource = new ClassPathResource(certificate);
            if (classPathResource.exists()) {
                // logger.info("Certificate found in classpath.");
                try (InputStream inputStream = classPathResource.getInputStream()) {
                    return inputStream.readAllBytes();
                }
            }
            
            File fileInRoot = new File(certificate);
            if (fileInRoot.exists()) {
                // logger.info("Certificate found in root directory.");
                return Files.readAllBytes(fileInRoot.toPath());
            }
            
            throw new IllegalArgumentException("Certificate file not found: " + certificate);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading certificate file: " + certificate, e);
        }
    }
    
	public HeaderMessage sendAuthentication() {
		HeaderMessageFactory factory = HeaderMessageFactoryImpl.getInstance();
		HeaderMessage authMessage = factory.buildSendAuthentication(getCertificateAsByteArray(), getDriverIdAsShort());
		LogUtils.logInfoMessageSendBytesToHexFormatt(authMessage.encode().array(), logger);
		return factory.buildSendAuthentication(getCertificateAsByteArray(), getDriverIdAsShort());

	}
    
    public static HeaderMessage decodeAuthenticationSuccessfull(ByteBuffer buffer, byte messageNumber) {
        AuthenticationSuccessfull authSuccess = new AuthenticationSuccessfull(messageNumber);
        buffer.getShort();
        short qtdeProps = buffer.get();
        
        for (int i = 0; i < qtdeProps; ++i) {
            byte identSize = buffer.get();
            String identification = Utils.getString(buffer, identSize);
            short valueSize = buffer.getShort();
            String value = Utils.getString(buffer, valueSize);
            authSuccess.addExtensibleProperty(identification, value);
        }
        
        return authSuccess;
    }
    
}