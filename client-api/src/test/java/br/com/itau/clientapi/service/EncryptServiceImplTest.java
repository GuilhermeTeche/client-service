package br.com.itau.clientapi.service;

import br.com.itau.clientapi.modules.cryptography.model.CommercialPlaceData;
import br.com.itau.clientapi.modules.cryptography.service.impl.EncryptServiceImpl;
import com.nimbusds.jose.JOSEException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.TestPropertySource;

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;

@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(locations = "classpath:test.properties")
public class EncryptServiceImplTest {

    @InjectMocks
    EncryptServiceImpl encryptServiceImpl;

    @Mock
    CommercialPlaceData commercialPlace;

    @Mock
    RSAPublicKey rsaPublicKey;


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testEncrypt() {

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGeneratesCryptographyExpectedException() throws JOSEException, NoSuchAlgorithmException {
        encryptServiceImpl.generatesCryptography(rsaPublicKey, commercialPlace);
    }
}