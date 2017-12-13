package com.xpressbees.things.dws.google_form;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xpressbees.things.dws.google_form.pojo.Content;
import com.xpressbees.things.dws.google_form.pojo.ContentListItem;
import com.xpressbees.things.dws.google_form.services.GoogleFormApi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    public static final String GOOGLE_FORM_URL = "d/e/1FAIpQLSct_kEvhsUbu78n-dnkWETVSaBUvxhLxrpeGM3mWyWmcQxQVg/viewform";
//    public static final String GOOGLE_FORM_URL = "d/15GXTuLkwJZx3VbyoHWnStfxIQii1Tq5CVWv6ho-6UT8/viewform";

    private static final String TAG = MainActivity.class.getSimpleName();
    ArrayList<ContentListItem> contentListItems = new ArrayList<>();
    private Document doc;
    private Content content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/")
                .build();
        GoogleFormApi service = retrofit.create(GoogleFormApi.class);


        service.getGoogleForm().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    ResponseBody body = response.body();
                    if (body != null) {
                        content = new Content();
                        String string = body.string();
                        Document doc = Jsoup.parse(string);
                        Elements elementsTitle = doc.getElementsByClass("freebirdFormviewerViewHeaderTitleRow");
                        Log.d(TAG, "titleElement: " + elementsTitle.text());
                        content.setTitle(elementsTitle.text());

                        Elements elementsDescription = doc.getElementsByClass("freebirdFormviewerViewHeaderDescription");
                        Log.d(TAG, "elementsDescription: " + elementsDescription.text());
                        content.setDescription(elementsDescription.text());

                        Elements elementsRequiredLegend = doc.getElementsByClass("freebirdFormviewerViewHeaderRequiredLegend");
                        Log.d(TAG, "elementsRequiredLegend: " + elementsRequiredLegend.text());
                        content.setRequiredLogo(elementsRequiredLegend.text());

                        Elements listElement = doc.getElementsByClass("freebirdFormviewerViewItemList");
                        Log.d(TAG, "onResponse: listElement list size:" + listElement.size());


                        Elements editTextlistElements = doc.getElementsByClass("freebirdFormviewerViewItemsItemItem freebirdFormviewerViewItemsTextTextItem");
                        Log.d(TAG, "\nonResponse: editTextlistElements list size:" + editTextlistElements.size());
                        for (int i = 0; i < editTextlistElements.size(); i++) {
                            Element elementInList = editTextlistElements.get(i);
                            ContentListItem contentListItem = new ContentListItem();

                            Elements elementInListHeader = elementInList.getElementsByClass("freebirdFormviewerViewItemsItemItemHeader");
                            Log.d(TAG, "\n onResponse: elementInListHeader: " + elementInListHeader.text());//text view
                            contentListItem.setHeader(elementInListHeader.text());

                            Elements required = elementInList.getElementsByClass("freebirdFormviewerViewItemsItemRequiredAsterisk");
                            Log.d(TAG, "\nonResponse: required: " + required.text());//text view
                            contentListItem.setRequired(!required.isEmpty());

                            //hint for simple paragraph text
                            Elements hintForEditTextParagraph = elementInList.getElementsByClass("quantumWizTextinputPapertextareaPlaceholder exportLabel");//hint paragraph text
                            Log.d(TAG, "\nonResponse: hintForEditTextParagraph: " + hintForEditTextParagraph.text());//hint
                            //hint for simple edit text
                            Elements hintForEditText = elementInList.getElementsByClass("quantumWizTextinputPaperinputPlaceholder exportLabel");//hint paragraph text
                            Log.d(TAG, "\nonResponse: hintForEditText: " + hintForEditText.text());//hint

                            if (hintForEditTextParagraph.isEmpty()) {
                                contentListItem.setHint(hintForEditText.text());
                            } else {
                                contentListItem.setHint(hintForEditTextParagraph.text());
                            }
                            contentListItems.add(contentListItem);

                        }
                        content.setContentListItems(contentListItems);


                        Elements multipleChoiceElements = doc.getElementsByClass("freebirdFormviewerViewItemsItemItemTitle freebirdCustomFont");
                        Log.d(TAG, "onResponse: multipleChoiceElements list size:" + multipleChoiceElements.size());


//                        Log.d(TAG, "onResponse: " + content.toString());

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });


    }

}
