// 代码生成时间: 2025-09-23 08:01:10
package com.example.imageresizer

import grails.transaction.Transactional
import groovy.util.logging.Slf4j
import org.springframework.web.multipart.MultipartFile
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException

@Slf4j
@Transactional
class ImageResizerService {

    // 调整图片尺寸的方法
    public List<File> resizeImages(List<MultipartFile> files, int newWidth, int newHeight) {
        List<File> resizedFiles = []
        
        files.each { MultipartFile file ->
            try {
                // 保存文件到服务器
                File tempFile = saveFile(file)
                // 读取图片文件
                BufferedImage originalImage = ImageIO.read(tempFile)
                
                // 创建一个新的 BufferedImage 对象，用于调整尺寸
                BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType())
                // 获取图片的Graphics对象，用于绘制
                Graphics2D g2d = resizedImage.createGraphics()
                // 设置图片平滑属性
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
                // 绘制图片
                g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null)
                g2d.dispose()
                
                // 保存调整尺寸后的图片
                File resizedFile = saveResizedFile(resizedImage)
                resizedFiles.add(resizedFile)
            } catch (IOException e) {
                log.error('Error resizing image', e)
            }
        }
        return resizedFiles
    }

    // 保存上传的图片文件到服务器
    private File saveFile(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename()
        File file = new File(grailsApplication.config.imageResizing.baseDir, fileName)
        multipartFile.transferTo(file)
        return file
    }

    // 保存调整尺寸后的图片文件到服务器
    private File saveResizedFile(BufferedImage image) {
        String fileName = "resized_${UUID.randomUUID()}.png"
        File file = new File(grailsApplication.config.imageResizing.baseDir, fileName)
        try {
            ImageIO.write(image, "png", file)
        } catch (IOException e) {
            log.error('Error saving resized image', e)
        }
        return file
    }
}
