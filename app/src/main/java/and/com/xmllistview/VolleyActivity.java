package and.com.xmllistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mancj.materialsearchbar.MaterialSearchBar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


//RequestQueue requestQueue;



public class VolleyActivity extends AppCompatActivity implements  MaterialSearchBar.OnSearchActionListener {

    TextView tv1;

    ArrayList<HashMap<String, String>> menuItems = new ArrayList<HashMap<String, String>>();

    ListView listView;
    MaterialSearchBar searchBar;
    private List<String> lastSearches;

    String url3 = "http://api.androidhive.info/pizza/?format=xml";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tv1 = (TextView) findViewById(R.id.a);
        listView = (ListView) findViewById(R.id.RecipeList);
        searchBar = (MaterialSearchBar) findViewById(R.id.searchBar);

        searchBar.setOnSearchActionListener(this);
        searchBar.setVisibility(View.VISIBLE);
        //restore last queries from disk
        //lastSearches = loadSearchSuggestionFromDisk();
        //searchBar.setLastSuggestions(list);
        //Inflate menu and setup OnMenuItemClickListener
        //searchBar.inflateMenu(R.menu.main);
        //searchBar.getMenu().setOnMenuItemClickListener();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchBar.setVisibility(View.GONE);
                callVolley();
            }
        });
    }


    public void callVolley() {

        String url1 = "http://coderzheaven.com/xml/test.xml";
        String url2 = "https://www.w3schools.com/xml/simple.xml";


        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest req = new StringRequest(Request.Method.GET, url3,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("Success: " + response);

                        try {

                            //InputStream is = getAssets().open(response);
                            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                            Document doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(response.getBytes("utf-8"))));
                            //System.out.println("Doc : " + doc);
                            System.out.print("Hello");

                            /*Element root = doc.getDocumentElement();             // Ist Way
                            NodeList items = root.getElementsByTagName("os");
                            Node item = items.item(0);
                            NodeList properties = item.getChildNodes();

                            Node property = properties.item(0);
                            String name = property.getNodeName();
                            System.out.print(name);
                            tv1.setText(name);*/

                          /* NodeList childNodes = doc.getChildNodes();                  // 3rd Way
                            iterateNodes(childNodes);
                                    */


                            Element element=doc.getDocumentElement();                     // // 2nd Way
                            element.normalize();

                            NodeList nList = doc.getElementsByTagName("item");
                            for (int i=0; i<nList.getLength(); i++) {

                                Node node = nList.item(i);
                                if (node.getNodeType() == Node.ELEMENT_NODE) {
                                    Element element2 = (Element) node;
                                    /*tv1.setText(tv1.getText()+"\n  Name : " + getValue("name", element2)+"\n");
                                    tv1.setText(tv1.getText()+"  COST : " + getValue("cost", element2)+"\n");
                                    tv1.setText(tv1.getText()+"  DES : " + getValue("description", element2)+"\n");
                                    tv1.setText(tv1.getText()+"  ID : " + getValue("id", element2)+"\n");
                                    tv1.setText(tv1.getText()+"-----------------------");
*/

                                    HashMap<String, String> map = new HashMap<String, String>();
                                    map.put("ID", getValue("id", element2));
                                    map.put("Name", getValue("name", element2));
                                    map.put("COST",  getValue("cost", element2));
                                    map.put("DES", getValue("description", element2));
                                    menuItems.add(map);


                                    ListAdapter adapter = new SimpleAdapter(getApplicationContext(), menuItems,
                                            R.layout.list_item,
                                            new String[] {"Name", "DES", "COST" }, new int[] {
                                            R.id.name, R.id.description, R.id.cost });

                                    listView.setAdapter(adapter);

                                    //ListView lv = getListView();




                                    // listening to single list item on click
                                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        public void onItemClick(AdapterView<?> parent, View view,
                                                                int position, long id) {

                                            // selected item
                                            String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
                                            String cost = ((TextView) view.findViewById(R.id.cost)).getText().toString();
                                            String description = ((TextView) view.findViewById(R.id.description)).getText().toString();
                                            // Launching new Activity on selecting single List Item
                                            Intent in = new Intent(VolleyActivity.this, SingleList.class);
                                            in.putExtra("Name", name);
                                            in.putExtra("COST", cost);
                                            in.putExtra("DES", description);
                                            startActivity(in);

                                        }
                                    });




                                }
                            }

                          /*  Node objects = doc.getDocumentElement();

                            for (Node object = objects.getFirstChild(); object != null; object = object.getNextSibling()) {
                                if (object instanceof Element) {
                                    Element e = (Element)object;
                                    if (e.getTagName().equalsIgnoreCase("circle")) {
                                        String color = e.getAttribute("color");
                                        System.out.println("It's a " + color + " circle!");
                                    } else if (e.getTagName().equalsIgnoreCase("rectangle")) {
                                        String text = e.getTextContent();
                                        System.out.println("It's a rectangle that says \"" + text + "\".");
                                    } else {
                                        System.out.println("I don't know what a " + e.getTagName() + " is for.");
                                    }
                                }
                            }*/





                        } catch (SAXException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Handled Error: " + error);
                    }
                }
        );
        queue.add(req);


    }


    private static String getValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }


    private  void iterateNodes(NodeList childNodes) {
        for (int i = 0; i < childNodes.getLength(); ++i) {
            Node node = childNodes.item(i);
            String text = node.getNodeValue();
            if (text != null && !text.trim().isEmpty()) {
                System.out.println(text);

            }
            if (node.hasChildNodes()) {
                iterateNodes(node.getChildNodes());
            }
        }

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //save last queries to disk
        //saveSearchSuggestionToDisk(searchBar.getLastSuggestions());
    }


    @Override
    public void onSearchStateChanged(boolean enabled) {
        //String s = enabled ? "enabled" : "disabled";
        //Toast.makeText(VolleyActivity.this, "Search " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSearchConfirmed(CharSequence text) {
        //startSearch(text.toString(), true, null, true);
        System.out.println(text.toString());

        if (text.toString().equals(url3)){
            callVolley();
            searchBar.setVisibility(View.GONE);
        }else {
            searchBar.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Search for http://api.androidhive.info/pizza/?format=xml ", Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onButtonClicked(int buttonCode) {

        //openVoiceRecognizer();

    }


    /*@Override
    public void onButtonClicked(int buttonCode) {
        switch (buttonCode){
            case MaterialSearchBar.BUTTON_NAVIGATION:
                //drawer.openDrawer(Gravity.LEFT);
                break;
            case MaterialSearchBar.BUTTON_SPEECH:
                openVoiceRecognizer();
        }*/


}


/*
*
* <os>
<id>1</id>
<name>Android</name>
<site>http://www.android.com</site>
</os>
*
*
*
* */