package pl.marsa.ytcrm.data.files_provider;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.marsa.ytcrm.data.DataProvider;
import pl.marsa.ytcrm.data.VideoDTO;

import java.util.List;

@Service
@Profile("classpath-files")
class FilesProvider implements DataProvider {
    @Override
    public List<VideoDTO> getVideoList() {
        return null;
    }
}