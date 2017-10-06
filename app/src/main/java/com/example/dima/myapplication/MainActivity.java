                                         package com.example.dima.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;


                                         public class MainActivity extends AppCompatActivity  {
   TextView xmlOut;
    private final String xmlUrl = "https://www.rmv.de/hapi/location.name?accessId=1e7f0454-2f9f-4bdc-b585-574bc9259c78&input=frankfurt%20hauptbahnhof";
    String[] stationen = { "Hauptwache", "Марья", "Петр", "Антон", "Даша", "Борис",
                                                     "Костя", "Игорь", "Анна", "Денис", "Андрей" };
    SortedMap StationenMap = new TreeMap();
    StationenMap.put();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       xmlOut = (TextView) findViewById(R.id.xmlText);
       getXMLasincTask xText = new getXMLasincTask();
       xText.execute();


       final AutoCompleteTextView  mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.selectStation1);
        mAutoCompleteTextView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, stationen));
        mAutoCompleteTextView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                mAutoCompleteTextView.showDropDown();
                return false;
            }
        });


    }
    public void submitOder(View view){
        TextView textView = (TextView) findViewById(R.id.countView);
        Integer num = Integer.parseInt(textView.getText().toString()) +1;
        textView.setText(num.toString());
        AutoCompleteTextView gt = (AutoCompleteTextView) findViewById(R.id.selectStation1);
        String text="Fullname: "+ gt.getText().toString();

        Toast.makeText(this, text,Toast.LENGTH_LONG).show();


    }



    class getXMLasincTask extends AsyncTask <String , Void , String>{

        @Override
        protected String doInBackground(String... voids) {
            try {
                // получаем xml парсер с настройками по умолчанию
                DocumentBuilder xml = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                // разбираем demo.xml и создаем Document
                Document doc = xml.parse("https://www.rmv.de/hapi/location.name?accessId=1e7f0454-2f9f-4bdc-b585-574bc9259c78&input=frankfurt%20hauptbahnhof");
                // корневой элемент документа
                Element rootel = doc.getDocumentElement();
                //-----------------------------------------
                // далее можно поиграться с методами DOM объектов
                // имя корневого элемента
                System.out.println(rootel.getNodeName());

                // список имен дочерних элементов
                System.out.println("Child elements: ");
                NodeList lst = rootel.getChildNodes();
                for (int i = 0; i < lst.getLength(); i++)
                    System.out.println(lst.item(i).getNodeName() + " ");

                // список имен дочерних элементов и их содержимого
                System.out.println("Child elements: ");
                Node el = rootel.getFirstChild();
                do {
                    System.out.println(el.getNodeName() + ": " + el.getTextContent());
                    el = el.getNextSibling();
                } while (el != null);
                //xmlOut.setText("fuck");
                return rootel.getNodeValue();

            } catch (Exception e) {
                e.printStackTrace();
                return e.toString();
                //return "Error!!!";
            }

        }
        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
           xmlOut.setText(result);
        }
    }






                                         }


