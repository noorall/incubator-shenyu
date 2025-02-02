/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shenyu.plugin.divide.subscriber;

import org.apache.shenyu.common.dto.MetaData;
import org.apache.shenyu.common.enums.RpcTypeEnum;
import org.apache.shenyu.plugin.base.cache.MetaDataCache;
import org.apache.shenyu.sync.data.api.MetaDataSubscriber;

/**
 * The type divide meta data subscriber.
 */
public class DivideMetaDataSubscriber implements MetaDataSubscriber {
    @Override
    public void onSubscribe(final MetaData metaData) {
        if (RpcTypeEnum.HTTP.getName().equals(metaData.getRpcType())) {
            // the update is also need to clean, but there is no way to
            // distinguish between crate and update, so it is always clean
            MetaDataCache.getInstance().clean();
        }
    }

    @Override
    public void unSubscribe(final MetaData metaData) {
        if (RpcTypeEnum.HTTP.getName().equals(metaData.getRpcType())) {
            MetaDataCache.getInstance().clean();
        }
    }

    @Override
    public void refresh() {
        MetaDataCache.getInstance().clean();
    }
}
