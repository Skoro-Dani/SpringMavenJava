package webservice.demo;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class RW {
    private static final SecureRandom secureRandom = new SecureRandom(); // threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); // threadsafe

    public static String LeggiDaFile(String nomeFile) throws FileNotFoundException, IOException {
        // variabile di appoggio
        File file = new File(nomeFile);
        String RIS = "";
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine();
        while (line != null) {
            RIS += line;
            RIS += "\n";
            line = br.readLine();
        }
        br.close();
        return RIS;
    }

    public static String LeggiPassword(String nomeFile) throws FileNotFoundException, IOException {
        // variabile di appoggio
        File file = new File(nomeFile);
        if (file.exists()) {
            String RIS = "";
            BufferedReader br = new BufferedReader(new FileReader(file));
            RIS = br.readLine();
            br.close();
            return RIS;
        } else
            return "";
    }

    public static void ScriviSuFileURL(String NomeFile) throws IOException {

        File f = new File(NomeFile);
        FileWriter fw = new FileWriter(f);

        fw.write("ciao");
        fw.flush();
        fw.close();

    }

    public static String md5(String input) {
        String md5 = null;
        if (null == input)
            return null;
        try {
            // Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");
            // Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());
            // Converts message digest value in base 16 (hex)
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    public static String AToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

}
