package br.com.itau.clientapi.service;

import br.com.itau.clientapi.modules.cryptography.service.impl.KeyPublicServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RunWith(MockitoJUnitRunner.class)
public class KeyPublicServiceImplTest {

    private static final String KEY_INVALID = "123";

    @InjectMocks
    KeyPublicServiceImpl keyPublicServiceImpl;

    @Before
    public void setUp() {
    }

    @Test(expected = InvalidKeySpecException.class)
    public void testGetKeyExpectInvalidKeySpecException() throws InvalidKeySpecException, NoSuchAlgorithmException {
        keyPublicServiceImpl.getPublicKey(KEY_INVALID);
    }
}