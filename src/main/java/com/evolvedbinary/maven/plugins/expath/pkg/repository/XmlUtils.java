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

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;

import static javax.xml.transform.OutputKeys.*;

class XmlUtils {
    static final DocumentBuilderFactory DOCUMENT_BUILDER_FACTORY = DocumentBuilderFactory.newInstance();
    static {
        DOCUMENT_BUILDER_FACTORY.setNamespaceAware(true);
    }
    static final TransformerFactory TRANSFORMER_FACTORY = TransformerFactory.newInstance();

    public static void serialize(final Document document, final Result result) throws TransformerException {
        final Transformer transformer = TRANSFORMER_FACTORY.newTransformer();
        transformer.setOutputProperty(ENCODING, "UTF-8");
        transformer.setOutputProperty(METHOD, "xml");
        transformer.setOutputProperty(INDENT, "yes");
        transformer.setOutputProperty(STANDALONE, "yes");
        transformer.setOutputProperty(OMIT_XML_DECLARATION, "yes");
        final DOMSource source = new DOMSource(document);
        transformer.transform(source, result);
    }
}
