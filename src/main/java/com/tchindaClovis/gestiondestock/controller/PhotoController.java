package com.tchindaClovis.gestiondestock.controller;

import com.tchindaClovis.gestiondestock.services.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.tchindaClovis.gestiondestock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/pictures")
//@RequiredArgsConstructor
public class PhotoController {
    private final MinioService minioService;

    @Autowired
    public PhotoController(MinioService minioService) {
        this.minioService = minioService;
    }

    @PostMapping("/upload")
    public String uploadPhoto(@RequestParam("file") MultipartFile file,
                              @RequestParam(value = "title", required = false) String title) {
        try {
            // Utilisation équivalente à l'ancien service Flickr
            return minioService.savePhoto(file.getInputStream(),
                    title != null ? title : file.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException("Erreur lecture fichier: " + e.getMessage(), e);
        }
    }

    @DeleteMapping
    public void deletePhoto(@RequestParam String photoUrl) {
        minioService.deletePhoto(photoUrl);
    }
}






//package com.tchindaClovis.gestiondestock.controller;
//
//import com.tchindaClovis.gestiondestock.services.MinioService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//
//@RestController
//@RequestMapping("/api/photos")
//public class PhotoController {
//
//    private MinioService minioService;
//    @Autowired
//    public PhotoController(MinioService minioService) {
//        this.minioService = minioService;
//    }
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadPhoto(@RequestBody byte[] photoData,
//                                              @RequestParam String title) {
//        try {
//            InputStream photoStream = new ByteArrayInputStream(photoData);
//            String photoUrl = minioService.savePhoto(photoStream, title);
//            return ResponseEntity.ok(photoUrl);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Erreur d'upload: " + e.getMessage());
//        }
//    }
//}








//package com.tchindaClovis.gestiondestock.controller;
//
//        import com.tchindaClovis.gestiondestock.services.MinioService;
//        import org.springframework.core.io.InputStreamResource;
//        import org.springframework.http.HttpHeaders;
//        import org.springframework.http.MediaType;
//        import org.springframework.http.ResponseEntity;
//        import org.springframework.web.bind.annotation.*;
//        import org.springframework.web.multipart.MultipartFile;
//
//        import java.util.List;
//
//@RestController
//@RequestMapping("/api/files")
//public class MinioController {
//
//    private final MinioService minioService;
//
//    public MinioController(MinioService minioService) {
//        this.minioService = minioService;
//    }
//
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
//        try {
//            String filename = file.getOriginalFilename();
//            minioService.uploadFile(file, filename);
//            return ResponseEntity.ok("Fichier uploadé avec succès: " + filename);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("Échec de l'upload: " + e.getMessage());
//        }
//    }
//
//    @GetMapping("/download/{filename}")
//    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String filename) {
//        try {
//            InputStream stream = minioService.downloadFile(filename);
//            InputStreamResource resource = new InputStreamResource(stream);
//
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .body(resource);
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/list")
//    public ResponseEntity<List<String>> listFiles() {
//        try {
//            List<String> files = minioService.listFiles();
//            return ResponseEntity.ok(files);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().build();
//        }
//    }
//
//    @DeleteMapping("/delete/{filename}")
//    public ResponseEntity<String> deleteFile(@PathVariable String filename) {
//        try {
//            minioService.deleteFile(filename);
//            return ResponseEntity.ok("Fichier supprimé: " + filename);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("Échec de la suppression: " + e.getMessage());
//        }
//    }
//
//    @GetMapping("/presigned-url/{filename}")
//    public ResponseEntity<String> getPresignedUrl(@PathVariable String filename) {
//        try {
//            String url = minioService.getPresignedUrl(filename);
//            return ResponseEntity.ok(url);
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("Erreur: " + e.getMessage());
//        }
//    }
//}






//    Contrôleur pour upload via MultipartFile
//
//@RestController
//@RequestMapping("/api/photos")
//public class PhotoController {
//
//    @Autowired
//    private MinioService minioService;
//
//    @PostMapping("/upload-multipart")
//    public ResponseEntity<String> uploadPhotoMultipart(@RequestParam("file") MultipartFile file,
//                                                       @RequestParam String title) {
//        try {
//            String photoUrl = minioService.savePhoto(file, title);
//            return ResponseEntity.ok(photoUrl);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Erreur d'upload: " + e.getMessage());
//        }
//    }
//
//    @DeleteMapping
//    public ResponseEntity<Void> deletePhoto(@RequestParam String photoUrl) {
//        try {
//            minioService.deletePhoto(photoUrl);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//}
