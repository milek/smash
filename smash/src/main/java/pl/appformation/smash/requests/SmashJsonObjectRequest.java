/*
 * Copyright (C) 2015-2017 Appformation sp. z o.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.appformation.smash.requests;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okio.Okio;
import pl.appformation.smash.SmashNetworkData;
import pl.appformation.smash.SmashRequest;
import pl.appformation.smash.SmashResponse;
import pl.appformation.smash.SmashResponse.FailedListener;
import pl.appformation.smash.SmashResponse.SuccessListener;
import pl.appformation.smash.errors.SmashError;

public class SmashJsonObjectRequest extends SmashRequest<JSONObject>
{

    public SmashJsonObjectRequest(@MethodRes int method, SuccessListener<JSONObject> successListener, FailedListener failedListener)
    {
        super(method, successListener, failedListener);
    }

    public SmashJsonObjectRequest(@MethodRes int method, String url, SuccessListener<JSONObject> successListener, FailedListener failedListener)
    {
        super(method, url, successListener, failedListener);
    }

    protected SmashResponse<JSONObject> parseResponse(SmashNetworkData data)
    {
        try
        {
            JSONObject json = new JSONObject(Okio.buffer(data.source).readUtf8());
            return SmashResponse.success(json);
        }
        catch (JSONException | IOException e)
        {
            return SmashResponse.failed(new SmashError(e));
        }
    }

}
