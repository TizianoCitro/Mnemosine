package mnemosine.controller;

import mnemosine.service.blob.BlobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/blob")
public class BlobContoller {

    @PostMapping("/upload")
    public void upload() {}

    @DeleteMapping("/delete")
    public void delete() {}

    @GetMapping("/download")
    public void download() {}

    @GetMapping("/blobs")
    public void blobs() {}

    @GetMapping("/info")
    public void info() {}

    @Autowired
    private BlobService blobService;
}
