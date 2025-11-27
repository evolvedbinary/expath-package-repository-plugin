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

import java.nio.file.Files
import java.nio.file.Path
import java.security.DigestInputStream
import java.security.MessageDigest

import static org.junit.Assert.*

final String GROUP_ID = "org.expath.pkg.it"
final String ARTIFACT_ID = "xquery-library"
final String VERSION = "1.0.0"


final Path target = basedir.toPath().resolve("target")
if (!Files.exists(target) || !Files.isDirectory(target)) {
    System.err.println("target directory is missing.")
    return false
}

final Path xarOutputDir = target.resolve("xars")

final Path functxXar = xarOutputDir.resolve("functx-1.0.1.xar")
if (!Files.exists(functxXar)) {
    System.err.println("xar file is missing.")
    return false
}

try (final InputStream actualIs = Files.newInputStream(functxXar)) {
    assertEquals("f1b6379b790c20e18b5b2784ad233bcb1149f6da586a4a29c546c7d4d99614e3", checksum(actualIs))
}

def checksum(final InputStream is) {
    MessageDigest md = MessageDigest.getInstance("SHA-256")
    new DigestInputStream(is, md).with { dis ->
        while (dis.read() != -1) { }

        md = dis.getMessageDigest()

        // bytes to hex
        StringBuilder result = new StringBuilder()
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b))
        }
        return result.toString()
    }
}