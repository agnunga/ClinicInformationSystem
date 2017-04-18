/** ****************************************************
 * Image resize servlet
 *
 *
 * Copyright (C) 2012 by Peter Hedenskog (http://peterhedenskog.com)
 *
 ******************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 *******************************************************
 */
package com.trial.trials;

import java.io.File;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Objects;

class Thumbnail {

    /**
     * Error message if the requested thumbnail original doesn't exist.
     */
    static final String ERROR_MESSAGE_ORIGINAL_IMAGE_DO_NOT_EXIST = "Requested non existing original image";

    /**
     * Error message if the requested thumbnail name isn't valid.
     */
    static final String ERROR_MESSAGE_THUMBNAIL_NAME_IS_NOT_VALID = "Thumbnail name isn't valid";
    /**
     * Error message if the requested thumbnail couldn't be created.
     *
     */
    static final String ERROR_MESSAGE_THUMBNAIL_NOT_CREATED = "Couldn't create thumbnail";

    /**
     * Error message if the requested thumbnail size isn't valid.
     */
    static final String ERROR_MESSAGE_THUMBNAIL_SIZE_IS_NOT_VALID = "Not a valid image size";
    /**
     * The regexp for the thumbnail name.
     */
    static final String MATCHING_NAME_REGEXP = ".+\\-[0-9]+x[0-9]+\\.(png|jpg|jpeg|gif)";

    private static final int BYTE = 8;
    private static final int MASK = 255;
    private final String destinationDir;
    private final String generatedFilePath;
    private final String imageDimensions;
    private final String imageFileExtension;
    private final String imageFileName;

    private final Logger logger = LoggerFactory
            .getLogger(Thumbnail.class);

    private final String originalBaseDir;

    private final String originalImageName;

    private final String originalImageNameWithExtension;

    private final Pattern pattern = Pattern.compile(MATCHING_NAME_REGEXP);

    /**
     * Create a thumbnail. Note will not check the file format.
     *
     * @param theFileName
     * @throws ThumbnailException
     */
    Thumbnail(String theFileName, String theOriginalBaseDir, String theDestinationBaseDir)
            throws ThumbnailException {

        if (theFileName == null || !pattern.matcher(theFileName).matches()) {
            throw new ThumbnailException(
                    ERROR_MESSAGE_THUMBNAIL_NAME_IS_NOT_VALID);
        }

        imageFileName = theFileName;
        originalImageName = imageFileName.substring(0,
                imageFileName.lastIndexOf("-"));
        imageFileExtension = imageFileName.substring(
                imageFileName.lastIndexOf("."), imageFileName.length());
        imageDimensions = imageFileName.substring(
                imageFileName.lastIndexOf("-") + 1,
                imageFileName.lastIndexOf("."));
        originalImageNameWithExtension = originalImageName + imageFileExtension;
        generatedFilePath = createFilePath();
        originalBaseDir = theOriginalBaseDir;
        destinationDir = theDestinationBaseDir + File.separator
                + generatedFilePath;

        final File dir = new File(destinationDir);

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                logger.error("Couldn't create dir {}",
                        dir.getAbsolutePath());
                throw new ThumbnailException(
                        ERROR_MESSAGE_THUMBNAIL_NOT_CREATED);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Thumbnail other = (Thumbnail) obj;
        if (destinationDir == null) {
            if (other.destinationDir != null) {
                return false;
            }
        } else if (!destinationDir.equals(other.destinationDir)) {
            return false;
        }
        if (imageFileName == null) {
            if (other.imageFileName != null) {
                return false;
            }
        } else if (!imageFileName.equals(other.imageFileName)) {
            return false;
        }
        if (originalBaseDir == null) {
            if (other.originalBaseDir != null) {
                return false;
            }
        } else if (!originalBaseDir.equals(other.originalBaseDir)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((destinationDir == null) ? 0 : destinationDir.hashCode());
        result = prime * result
                + ((imageFileName == null) ? 0 : imageFileName.hashCode());
        result = prime * result
                + ((originalBaseDir == null) ? 0 : originalBaseDir.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("imageFileName", imageFileName)
                .add("imageDimensions", imageDimensions)
                .add("originalBaseDir", originalBaseDir)
                .add("destinationDir", destinationDir).toString();

    }

    String getDestinationDir() {
        return destinationDir;
    }

    /**
     * Get the generated the file path, will always use the original filename,
     * so that all sizes of one file end up in one directory.
     *
     * @return the path in the style of two dirs example /205/070/
     */
    String getGeneratedFilePath() {
        return generatedFilePath;
    }

    String getImageDimensions() {
        return imageDimensions;
    }

    String getImageFileExtension() {
        return imageFileExtension;
    }

    String getImageFileName() {
        return imageFileName;
    }

    String getOriginalBaseDir() {
        return originalBaseDir;
    }

    String getOriginalImageName() {
        return originalImageName;
    }

    String getOriginalImageNameWithExtension() {
        return originalImageNameWithExtension;
    }

    /**
     * Create the generated the file path, will always use the original
     * filename, so that all sizes of one file end up in one directory.
     *
     * @return the path in the style of two dirs example /205/070/
     */
    private String createFilePath() {

        // setup the thumbs dir based on the original name, so that all sizes
        // are in the same working dir
        final int hashcode = originalImageNameWithExtension.hashCode();

        final StringBuilder path = new StringBuilder(File.separator);
        // first dir
        path.append(String.format("%03d", hashcode & MASK));
        path.append(File.separator);
        // second dir
        path.append(String.format("%03d", (hashcode >> BYTE) & MASK));
        path.append(File.separator);

        return path.toString();
    }
}
