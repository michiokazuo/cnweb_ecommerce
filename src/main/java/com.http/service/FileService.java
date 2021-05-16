package com.http.service;

import javax.servlet.http.Part;
import java.util.Collection;
import java.util.List;

public interface FileService {

    List<String> uploadFiles(Collection<Part> partCollection) throws Exception;

}
