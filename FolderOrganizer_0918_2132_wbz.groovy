// 代码生成时间: 2025-09-18 21:32:07
package com.example.organizer

import groovy.io.FileType
import groovy.io.FileVisitResult
import org.codehaus.groovy.ast.ClassNode

import java.nio.file.DirectoryStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes

/**
 * FolderOrganizer is a program that organizes files in a directory.
 * It sorts files into subdirectories based on their extensions.
 */
class FolderOrganizer extends SimpleFileVisitor<Path> {

    private final Path startPath;
    private final Map<String, List<Path>> filesMap = new HashMap<>();
    private final Set<String> createdDirs = new HashSet<>();

    /**
     * Constructor for FolderOrganizer.
     * @param startPath the directory path to start organizing.
     */
    FolderOrganizer(Path startPath) {
        this.startPath = startPath;
    }

    /**
     * Start the organization process.
     */
    void organize() {
        try {
            Files.walkFileTree(startPath, this);
            filesMap.each { extension, paths ->
                Path dir = startPath.resolve(extension)
                if (!createdDirs.contains(dir.toString())) {
                    Files.createDirectories(dir)
                    createdDirs.add(dir.toString())
                }
                paths.each {
                    Files.move(it, dir.resolve(it.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        } catch (IOException e) {
            println "Error organizing files: ${e.message}"
        }
    }

    /**
     * Visits each file in the directory.
     * @param file the file being visited.
     * @param attrs the file attributes.
     * @return the result of the visit.
     */
    @Override
    FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if (Files.isRegularFile(file)) {
            String extension = getExtension(file);
            filesMap.computeIfAbsent(extension, k -> new ArrayList<>()).add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    /**
     * Helper method to extract the file extension.
     * @param file the file to extract the extension from.
     * @return the file extension.
     */
    private String getExtension(Path file) {
        String fileName = file.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
    }

    /**
     * Main method to run the FolderOrganizer.
     * @param args the command line arguments (directory path).
     */
    static void main(String[] args) {
        if (args.length < 1) {
            println "Usage: groovy FolderOrganizer <directory_path>"
            return;
        }

        Path path = Paths.get(args[0]);
        if (!Files.isDirectory(path)) {
            println "The specified path is not a directory."
            return;
        }

        FolderOrganizer organizer = new FolderOrganizer(path);
        organizer.organize();
        println "Files have been organized."
    }
}
