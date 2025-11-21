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

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import java.io.File;

public class ResolveMojoTest extends AbstractMojoTestCase {

    @Override
    protected void setUp() throws Exception {
        // required for mojo lookups to work
        getContainer().getContext().put("project.groupId", "blah");
        super.setUp();

    }

    public void testMojoGoal() throws Exception {
        final File testPom = new File(getBasedir(), "src/test/resources/unit/resolve-basic-test/pom.xml");
        final ResolveMojo mojo = (ResolveMojo) lookupMojo("resolve", testPom);
        assertNotNull(mojo);

        assertEquals("http://some-other-repo.com", mojo.getRepoUri());
        final PackageInfo packageInfo = new PackageInfo("no-sha256", "1.0.0", "some-package.xar");
        assertEquals("http://some-other-repo.com/public/some-package.xar", mojo.getPackageUri(packageInfo));
    }
}
