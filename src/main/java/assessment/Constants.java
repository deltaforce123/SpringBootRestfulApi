package assessment;

public interface Constants {

    int MAX_REQUEST_SIZE = 1024 * 1024 * 5 * 5;
    int MAX_FILE_SIZE = 30485760;
    int FILE_SIZE_THRESHOLD = 1024 * 1024;
    String TMP_LOCATION = "/tmp";


    interface JsonKeys {
        String ID = "id";
        String NAME = "name";
        String SIZE = "size";
        String CONTENT_TYPE = "contentType";
        String COUNT = "count";
    }
}
