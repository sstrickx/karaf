/*
 *   Copyright 2005 The Apache Software Foundation
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package org.apache.felix.framework;

import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

class SystemBundleActivator implements BundleActivator
{
    private Felix m_felix = null;
    private List m_activatorList = null;
    private BundleContext m_context = null;

    SystemBundleActivator(Felix felix, List activatorList)
    {
        this.m_felix = felix;
        this.m_activatorList = activatorList;
    }

    public void start(BundleContext context) throws Exception
    {
        this.m_context = context;

        // Start all activators.
        if (m_activatorList != null)
        {
            for (int i = 0; i < m_activatorList.size(); i++)
            {
                ((BundleActivator) m_activatorList.get(i)).start(context);
            }
        }
    }

    public void stop(BundleContext context) throws Exception
    {
        if (m_activatorList != null)
        {
            // Stop all activators.
            for (int i = 0; i < m_activatorList.size(); i++)
            {
                ((BundleActivator) m_activatorList.get(i)).stop(context);
            }
        }
    }

    public BundleContext getBundleContext()
    {
        return m_context;
    }
}