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

import com.evolvedbinary.j8fu.tuple.Tuple2;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.ArrayList;
import java.util.List;

import static com.evolvedbinary.j8fu.tuple.Tuple.Tuple;

public class Package {

    @Parameter
    private String name;

    @Parameter
    private String abbrev;

    @Parameter
    private String version;

    @Parameter
    private String semanticVersion;

    @Parameter
    private String semanticVersionMin;

    @Parameter
    private String semanticVersionMax;

    public String getName() {
        return name;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public String getVersion() {
        return version;
    }

    public String getSemanticVersion() {
        return semanticVersion;
    }

    public String getSemanticVersionMin() {
        return semanticVersionMin;
    }

    public String getSemanticVersionMax() {
        return semanticVersionMax;
    }

    @Override
    public String toString() {
        final List<Tuple2<String, String>> tuples = new ArrayList<>();
        if (getName() != null) {
            tuples.add(Tuple("name", getName()));
        }
        if (getAbbrev() != null) {
            tuples.add(Tuple("abbrev", getAbbrev()));
        }
        if (getVersion() != null) {
            tuples.add(Tuple("version", getVersion()));
        }
        if (getSemanticVersion() != null) {
            tuples.add(Tuple("semantic-version", getSemanticVersion()));
        }
        if (getSemanticVersionMin() != null) {
            tuples.add(Tuple("semantic-version-min", getSemanticVersionMin()));
        }
        if (getSemanticVersionMax() != null) {
            tuples.add(Tuple("semantic-version-max", getSemanticVersionMax()));
        }

        final StringBuilder buf = new StringBuilder();
        buf.append('{');
        for (int i = 0; i < tuples.size(); i++) {
            buf.append('"').append(tuples.get(i)._1).append('"').append(": ")
                    .append('"').append(tuples.get(i)._2).append('"');

            if (i < tuples.size() - 1) {
                buf.append(", ");
            }
        }
        buf.append('}');

        return buf.toString();
    }


}
