import org.iq80.leveldb.api.DB;
import org.iq80.leveldb.api.Options;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.iq80.leveldb.impl.Iq80DBFactory.asString;
import static org.iq80.leveldb.impl.Iq80DBFactory.bytes;

public final class ApiTest {

    @Test
    void testOpen() throws IOException {
        Options options = new Options();
        options.createIfMissing(true);
        try (DB db = Iq80DBFactory.factory.open(new File("example"), options)) {
            System.out.println(db != null);
        }
    }


    @Test
    void testPutAndGet() throws IOException {
        Options options = new Options();
        options.createIfMissing(true);
        try (DB db = Iq80DBFactory.factory.open(new File("example"), options)) {
            db.put(bytes("Tampa"), bytes("rocks"));
            String value = asString(db.get(bytes("Tampa")));
            System.out.println(value);
        }
    }


    @Test
    void testDelete() throws IOException {
        Options options = new Options();
        options.createIfMissing(true);
        try (DB db = Iq80DBFactory.factory.open(new File("example"), options)) {
            db.put(bytes("Tampa"), bytes("rocks"));
            System.out.println(asString(db.get(bytes("Tampa"))));
            db.delete(bytes("Tampa"));
            System.out.println(asString(db.get(bytes("Tampa"))));
        }
    }

}
