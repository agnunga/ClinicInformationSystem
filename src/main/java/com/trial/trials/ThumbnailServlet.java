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
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "ThumbnailServlet", urlPatterns = "/upload_thumbnail")
public class ThumbnailServlet extends HttpServlet {

    /**
     * The name of the servlet init parameter for the request parameter.
     */
    private static final String INIT_PARAMETER_IMG_REQUEST_PARAMETER = "image-request-parameter-name";

    /**
     * The name of the servlet init parameter for where the original images are
     * located.
     */
    private static final String INIT_PARAMETER_ORIGINAL_WEB_DIR = "originals-dir";
    /**
     * The name of the servlet init parameter for the base dir for thumbnail
     * images.
     */
    private static final String INIT_PARAMETER_THUMB_WEB_DIR = "thumbs-dir";

    /**
     * The name of the servlet init parameter for valid image sizes.
     */
    private static final String INIT_PARAMETER_VALID_SIZES = "valid-sizes";

    private static final long serialVersionUID = -2092311650388075782L;

    /**
     * Only a number to make sure thumbnails aren't created more than once (at a
     * time).
     */
    private static final int CACHE_MAX_SIZE = 10000;

    /**
     * My logger.
     */
    private final transient Logger logger = LoggerFactory
            .getLogger(ThumbnailServlet.class);

    /**
     * Cache.
     */
    private final Cache<Thumbnail, File> cache = CacheBuilder.newBuilder()
            .maximumSize(CACHE_MAX_SIZE).build();

    /**
     * The factory classes, used to create thumbnail objects.
     */
    private transient ThumbnailFetcher factory;

    /**
     * Directory for originals.
     */
    private String originalsDir;

    /**
     * The name of the request parameter.
     */
    private String requestParameterName;

    /**
     * Web thumbnail directory.
     */
    private String thumbsDir;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        final String sizes = config
                .getInitParameter(INIT_PARAMETER_VALID_SIZES);

        final Set<String> validSizes = new HashSet<String>();
        if (sizes != null) {
            final StringTokenizer token = new StringTokenizer(sizes, ",");
            while (token.hasMoreTokens()) {
                validSizes.add(token.nextToken());
            }
        } else {
            logger.info("Running " + getServletName()
                    + " without configured valid "
                    + "sizes, use the servlet init parameter "
                    + INIT_PARAMETER_VALID_SIZES + " to set it up.");
        }

        requestParameterName = config
                .getInitParameter(INIT_PARAMETER_IMG_REQUEST_PARAMETER);
        thumbsDir = config.getInitParameter(INIT_PARAMETER_THUMB_WEB_DIR);
        originalsDir = config.getInitParameter(INIT_PARAMETER_ORIGINAL_WEB_DIR);

        factory = new ThumbnailFetcher(getServletContext().getRealPath("/")
                + originalsDir, getServletContext()
                        .getRealPath("/" + thumbsDir), validSizes);

        logger.info(getServletName()
                + " is configured with request parameter name:"
                + requestParameterName + " originalsDir:" + originalsDir
                + " thumbDir:" + thumbsDir + " and valid sizes:"
                + (sizes == null ? "" : sizes));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.print("<p>\n"
                + "  A resize image servlet, that can resize an image on demand. Will resize an\n"
                + "  already existing original image to different configured size(s).\n"
                + "  \n"
                + "  User scenario:\n"
                + "  </p>\n"
                + "  <ol>\n"
                + "  <li>Access the servlet with a name that holds the new size of the original\n"
                + "  image <i>/SERVLET/?img=MY_ORIGINAL_IMAGE-120x94.png</i> (need to be of the\n"
                + "  format of {@link Thumbnail#MATCHING_NAME_REGEXP}</li>\n"
                + "  <li>The servlet will check if the original image exist (named\n"
                + "  MY_ORIGINAL_IMAGE.png in this example), meaning it is a valid request</li>\n"
                + "  <li>if the new size is configured, the image can</li>\n"
                + "  <li>The servlet will check if that size of the image already exist on disk,\n"
                + "  if it exist, it will be returned.\n"
                + "  <li>\n"
                + "  <li>If the images don't exist, the thumbnail of the requested size will be\n"
                + "  created by the calling thread. The creation is thread safe and only one thread \n"
                + "  can create the one & same thumbnail (but other threads can create others, at the same time).</li>\n"
                + "  <li>The image is returned</li>\n"
                + "  </ol>\n"
                + "  \n"
                + "  <p>\n"
                + "  The servlet <b>needs</b> to be configured by the following parameters:\n"
                + "  <ul>\n"
                + "  <li>{@link #INIT_PARAMETER_VALID_SIZES} that will configure the valid image\n"
                + "  sizes. Configure by the format: <i>460x360,220x172,120x94</i></li>\n"
                + "  <li>{@link #INIT_PARAMETER_ORIGINAL_WEB_DIR} that is the relative path to the\n"
                + "  orignals web folder.</li>\n"
                + "  <li>{@link #INIT_PARAMETER_THUMB_WEB_DIR} that is the base web dir for the\n"
                + "  thumbnails.</li>\n"
                + "  <li>{@link #INIT_PARAMETER_IMG_REQUEST_PARAMETER} that is the name of the\n"
                + "  request parameter that will hold the value of the image that you want to\n"
                + "  fetch.</li>\n"
                + "  </ul>\n"
                + "  </p>\n"
                + "  \n"
                + "  <p>\n"
                + "  Note: No cache header is added within the servlet.\n"
                + "  </p>"
        );
        final String thumbnailName = req.getParameter(requestParameterName);

        if (logger.isDebugEnabled()) {
            logger.debug("Get thumbnail named {}", thumbnailName);
        }

        if (thumbnailName == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Accessed with no thumbnail name");
            }
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    Thumbnail.ERROR_MESSAGE_THUMBNAIL_NAME_IS_NOT_VALID);
            return;
        }

        try {
            final Thumbnail thumbnail = factory.get(thumbnailName);

            if (!doTheThumbnailExist(thumbnail)) {

                try {
                    // kind of a simple hack to make sure only 
                    // one thread creates the actual thumbnail,  
                    // think the Memoizer
                    cache.get(thumbnail, new ImageMagickFileThumbnailCreator(
                            thumbnail));
                } catch (ExecutionException e) {
                    if (logger.isErrorEnabled()) {
                        logger.error("Couldn't create thumbnail", e.getCause());
                    }
                    resp.sendError(
                            HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            Thumbnail.ERROR_MESSAGE_THUMBNAIL_NOT_CREATED);
                    return;
                }
            }
            returnTheThumbnail(req, resp, thumbnail);
        } catch (ThumbnailException e1) {
            if (logger.isDebugEnabled()) {
                logger.debug("Thumbnail not created beacuse {}", e1.getReason());
            }
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e1.getReason());
            return;
        } catch (IOException e) {
            if (logger.isErrorEnabled()) {
                logger.error("Couldn't create thumbnail", e);
            }
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    Thumbnail.ERROR_MESSAGE_THUMBNAIL_NOT_CREATED);
            return;
        }

    }

    /**
     * Check if the thumbnail already exists.
     *
     * @param thumbnail the thumbnail
     * @return true if the thumbnails exist
     */
    protected boolean doTheThumbnailExist(Thumbnail thumbnail) {
        final File theImageThumbnail = new File(thumbnail.getDestinationDir()
                + thumbnail.getImageFileName());

        return theImageThumbnail.exists();
    }

    /**
     * Return the thumbnail to the user.
     *
     * @param req the request
     * @param resp the response
     * @param thumbnail the thumbnail that will be returned
     * @throws ServletException if the thumbnail couldn't be delivered
     * @throws IOException if the thumbnail couldn't be delivered
     */
    protected void returnTheThumbnail(HttpServletRequest req,
            HttpServletResponse resp, Thumbnail thumbnail)
            throws ServletException, IOException {
        final RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/" + thumbsDir + thumbnail.getGeneratedFilePath()
                + thumbnail.getImageFileName());
        rd.forward(req, resp);
    }
}
