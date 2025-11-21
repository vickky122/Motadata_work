package playlist;

import java.util.List;

public interface PlaylistOperations {
    boolean addAtBeginning(String title);
    boolean addAtEnd(String title);
    boolean deleteSong(String title);
    List<String> getForwardList();
    List<String> getBackwardList();
    int size();
}
