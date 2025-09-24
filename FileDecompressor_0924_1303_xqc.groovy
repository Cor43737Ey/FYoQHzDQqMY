// 代码生成时间: 2025-09-24 13:03:30
class FileDecompressor {

    // Method to decompress a ZIP file
    // inputPath: the path to the ZIP file
    // outputPath: the directory where the files will be extracted
    void decompressZip(String inputPath, String outputPath) {
        try {
            // Create a ZIP file input stream
            FileInputStream fis = new FileInputStream(inputPath)
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis))

            // Create a new directory if it doesn't exist
            File directory = new File(outputPath)
            if (!directory.exists()) {
                directory.mkdirs()
            }

            // Iterate over the entries in the ZIP file
            ZipEntry zipEntry = zis.nextEntry
            while (zipEntry != null) {
                File newFile = new File(outputPath + File.separator + zipEntry.name)

                // Create directories for subdirectories in ZIP
                if (zipEntry.isDirectory()) {
                    newFile.mkdirs()
                } else {
                    // Create parent directories if they don't exist
                    newFile.parentFile.mkdirs()

                    // Extract file
                    FileOutputStream fos = new FileOutputStream(newFile)
                    IOUtils.copy(zis, fos)
                    fos.close()
                }

                zipEntry = zis.nextEntry
            }
            zis.closeEntry()
            zis.close()

            println "Files have been decompressed to: $outputPath"
        } catch (IOException e) {
            // Error handling
            e.printStackTrace()
            println "An error occurred while decompressing the file: $inputPath"
        }
    }

    // Method to decompress a TAR file
    // inputPath: the path to the TAR file
    // outputPath: the directory where the files will be extracted
    void decompressTar(String inputPath, String outputPath) {
        try {
            // Create a TAR file input stream
            FileInputStream fis = new FileInputStream(inputPath)
            TarInputStream tais = new TarInputStream(new BufferedInputStream(fis))

            // Create a new directory if it doesn't exist
            File directory = new File(outputPath)
            if (!directory.exists()) {
                directory.mkdirs()
            }

            // Iterate over the entries in the TAR file
            TarEntry tarEntry
            while ((tarEntry = tais.nextEntry) != null) {
                File newFile = new File(outputPath + File.separator + tarEntry.name)

                // Create directories for subdirectories in TAR
                if (tarEntry.isDirectory()) {
                    newFile.mkdirs()
                } else {
                    // Create parent directories if they don't exist
                    newFile.parentFile.mkdirs()

                    // Extract file
                    FileOutputStream fos = new FileOutputStream(newFile)
                    IOUtils.copy(tais, fos)
                    fos.close()
                }
            }
            tais.close()

            println "Files have been decompressed to: $outputPath"
        } catch (IOException e) {
            // Error handling
            e.printStackTrace()
            println "An error occurred while decompressing the file: $inputPath"
        }
    }

    // Main method to test the decompressor
    static void main(String[] args) {
        // Instantiate the FileDecompressor
        FileDecompressor decompressor = new FileDecompressor()

        // Define the input and output paths
        String zipInputPath = "path/to/your.zip"
        String tarInputPath = "path/to/your.tar"
        String outputPath = "path/to/output"

        // Decompress ZIP file
        decompressor.decompressZip(zipInputPath, outputPath)

        // Decompress TAR file
        decompressor.decompressTar(tarInputPath, outputPath)
    }
}