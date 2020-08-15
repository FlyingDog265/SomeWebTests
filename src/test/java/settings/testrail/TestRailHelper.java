package settings.testrail;

import com.codeborne.selenide.Selenide;
import com.google.gson.GsonBuilder;
import helpers.DateHelper;
import okhttp3.*;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("UnusedReturnValue")
public class TestRailHelper {
    public static final String BASE_URL = "https://testrail.ru/testrail/";
    private static final String LOGIN = "login";
    private static final String PASS = "password";

    private static final OkHttpClient client = new OkHttpClient.Builder().authenticator((route, response) -> {
        Request request = response.request();
        if (request.header("Authorization") != null) {
            return null;
        }
        return request.newBuilder()
                .header("Authorization", Credentials.basic(LOGIN, PASS))
                .build();
    }).build();

    public static Response sendTestResult(String runId, String caseId, int status, String comment, String defects) throws IOException {
        TestRailRequestModel requestModel = new TestRailRequestModel(
                status,
                comment,
                "Прогон от " + DateHelper.getDate("dd MMM yyyy", 0),
                defects
        );

        String stringBody = new GsonBuilder()
                .serializeNulls()
                .create()
                .toJson(requestModel);

        RequestBody body = RequestBody.create(MediaType.parse("JSON"), stringBody);

        Request request = new Request.Builder()
                .url(BASE_URL + "index.php?/api/v2/add_result_for_case/" + runId + "/" + caseId)
                .header("Content-Type", "application/json")
                .post(body)
                .build();
        return client.newCall(request).execute();
    }

    public static Response attachScreenshot(int resultId, ITestResult result) throws IOException {
        String filename = Selenide.screenshot(result.getMethod().getMethodName());
        File screenshot = new File(filename);

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(
                        "attachment",
                        filename,
                        RequestBody.create(MediaType.parse("image/png"), screenshot)
                )
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "index.php?/api/v2/add_attachment_to_result/" + resultId)
                .addHeader("Content-Type", "multipart/form-data")
                .post(body)
                .build();

        return client.newCall(request).execute();
    }
}
