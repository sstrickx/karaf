/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.karaf.features.internal.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.karaf.features.internal.deployment.Downloader;
import org.apache.karaf.features.internal.deployment.StreamProvider;
import org.apache.karaf.features.internal.util.MultiException;

public class SimpleDownloader implements Downloader {

    private final MultiException exception = new MultiException("Error");

    @Override
    public void await() throws InterruptedException, MultiException {
        exception.throwIfExceptions();
    }

    @Override
    public void download(final String location, final DownloadCallback downloadCallback) throws MalformedURLException {
        final URL url = new URL(location);
        try {
            downloadCallback.downloaded(new StreamProvider() {
                @Override
                public InputStream open() throws IOException {
                    return url.openStream();
                }
            });
        } catch (Exception e) {
            exception.addException(e);
        }
    }
}
