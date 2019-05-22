package es.upm.miw.documents;

import es.upm.miw.business_services.Encrypting;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class EncryptTest {

    @Test
    void testEncryptInBase64UrlSafe() {
        String url = new Encrypting().encryptInBase64UrlSafe();
        assertEquals(-1, url.indexOf("+"));
        assertEquals(-1, url.indexOf("/"));
        assertEquals(-1, url.indexOf("="));
    }

}
