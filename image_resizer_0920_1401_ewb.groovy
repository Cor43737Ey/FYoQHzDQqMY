// 代码生成时间: 2025-09-20 14:01:30
package com.example.util

import grails.io.ImageIO
import grails.io.FilenameUtils
import groovy.io.FileType
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import javax.imageio.ImageIO as JavaImageIO
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException

/**
 * Image Resizer Utility class.
 * This class provides a utility for batch resizing of images.
 */
@Slf4j
@CompileStatic
class ImageResizer {
    private static final int DEFAULT_MAX_WIDTH = 800
    private static final int DEFAULT_MAX_HEIGHT = 600

    /**
     * Resizes images in the specified directory and its subdirectories.
     *
     * @param directoryPath the path to the directory containing images to resize
     * @param maxWidth the maximum width of the resized images
     * @param maxHeight the maximum height of the resized images
     */
    void resizeImagesInDirectory(String directoryPath, int maxWidth = DEFAULT_MAX_WIDTH, int maxHeight = DEFAULT_MAX_HEIGHT) {
        File directory = new File(directoryPath)
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("The specified directory does not exist or is not a directory.")
        }

        directory.eachFileRecurse { File file ->
            if (FilenameUtils.isImageFile(file.name)) {
                try {
                    resizeImage(file, maxWidth, maxHeight)
                } catch (IOException e) {
                    log.error("Failed to resize image: ${file.path}", e)
                }
            }
        }
    }

    /**
     * Resizes a single image.
     *
     * @param imageFile the image file to resize
     * @param maxWidth the maximum width of the resized image
     * @param maxHeight the maximum height of the resized image
     * @throws IOException if an I/O error occurs during processing the image
     */
    private void resizeImage(File imageFile, int maxWidth, int maxHeight) throws IOException {
        BufferedImage originalImage = JavaImageIO.read(imageFile)
        double widthRatio = maxWidth / (double) originalImage.width
        double heightRatio = maxHeight / (double) originalImage.height
        double ratio = Math.min(widthRatio, heightRatio)
        int newWidth = (int) (originalImage.width * ratio)
        int newHeight = (int) (originalImage.height * ratio)

        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.type)
        resizedImage.getGraphics().drawImage(
                originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH),
                0, 0, null
        )

        File resizedFile = new File(imageFile.parent, "resized_${imageFile.name}")
        JavaImageIO.write(resizedImage, FilenameUtils.getExtension(imageFile.name), resizedFile)
    }
}
