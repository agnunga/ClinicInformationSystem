package com.agunga.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload_file")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, location = "/temp", maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class UploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private boolean isMultipart;
    private String filePath;
    private final int maxFileSize = 50 * 1024;
    private final int maxMemSize = 4 * 1024;
    private File file;

    @Override
    public void init() {
// Get the file location where it would be stored.
        filePath = getServletContext().getInitParameter("file-upload");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/users/uploadFile.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (!isMultipart) {
            out.print("File not uploaded");
        }
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        dfif.setSizeThreshold(maxFileSize);
        dfif.setRepository(new File("D:\\TEMPX"));
        ServletFileUpload sfu = new ServletFileUpload(dfif);
        sfu.setSizeMax(maxFileSize);
        try {
            List<FileItem> fileItems = sfu.parseRequest(request);
            Iterator i = fileItems.iterator();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String conntentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();

                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath + fileName.substring((fileName.lastIndexOf("\\"))));
                    } else {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fi.write(file);
                    out.println("Uploaded Filename: " + fileName + "<br>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

}
