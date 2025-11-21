/*
 * EXPath Package Repository Maven Plugin
 * Copyright (C) 2024, Evolved Binary Ltd
 *
 * admin@evolvedbinary.com
 * https://www.evolvedbinary.com
 *
 * SPDX-License-Identifier: BUSL-1.1
 *
 * Use of this software is governed by the Business Source License 1.1
 * included in the LICENSE file and at www.mariadb.com/bsl11.
 *
 * Change Date: 2028-11-21
 *
 * On the date above, in accordance with the Business Source License, use
 * of this software will be governed by the Apache License, Version 2.0.
 *
 * Additional Use Grant: Production use of the Licensed Work for a permitted
 * purpose. A Permitted Purpose is any purpose other than a Competing Use.
 * A Competing Use means making the Software available to others in a commercial
 * product or service that: substitutes for the Software; substitutes for any
 * other product or service we offer using the Software that exists as of the
 * date we make the Software available; or offers the same or substantially
 * similar functionality as the Software.
 */
package com.evolvedbinary.maven.plugins.expath.pkg.repository;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.TransformerException;

import java.io.IOException;

import static com.evolvedbinary.maven.plugins.expath.pkg.repository.XmlUtils.DOCUMENT_BUILDER_FACTORY;
import static com.evolvedbinary.maven.plugins.expath.pkg.repository.XmlUtils.TRANSFORMER_FACTORY;

public class PackageInfo {

    public static final String METADATA_FILE_EXTENSION = ".xml";

    private final String sha256;
    private final String version;
    private final String path;

    public PackageInfo(final String sha256, final String version, final String path) {
        this.sha256 = sha256;
        this.version = version;
        this.path = path;
    }

    public String getSha256() {
        return sha256;
    }

    public String getVersion() {
        return version;
    }

    public String getPath() {
        return path;
    }

    public void serialize(final Result result) throws ParserConfigurationException, TransformerException {
        final DocumentBuilder documentBuilder = DOCUMENT_BUILDER_FACTORY.newDocumentBuilder();
        final Document document = documentBuilder.newDocument();
        final Element element = document.createElement("packageInfo");
        element.setAttribute("sha256", getSha256());
        element.setAttribute("version", getVersion());
        element.setAttribute("path", getPath());
        document.appendChild(element);

        XmlUtils.serialize(document, result);
    }

    public static PackageInfo deserialize(final InputSource source) throws ParserConfigurationException, IOException, SAXException {
        final DocumentBuilder documentBuilder = DOCUMENT_BUILDER_FACTORY.newDocumentBuilder();
        final Document document = documentBuilder.parse(source);
        final Element element = document.getDocumentElement();

        final String sha256 = element.getAttribute("sha256");
        final String version = element.getAttribute("version");
        final String path = element.getAttribute("path");

        return new PackageInfo(sha256, version, path);
    }
}
