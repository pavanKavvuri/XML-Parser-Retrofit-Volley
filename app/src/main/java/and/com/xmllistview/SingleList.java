package and.com.xmllistview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SingleList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_list);

        TextView name = ((TextView) findViewById(R.id.Sname));
        TextView cost = ((TextView) findViewById(R.id.Scost));
        TextView description = ((TextView) findViewById(R.id.Sdescription));

        Intent i = getIntent();
        String Name = i.getStringExtra("Name");
        String Cost = i.getStringExtra("COST");
        String Des = i.getStringExtra("DES");

        name.setText(Name);
        cost.setText(Cost);
        description.setText(Des);
    }
}
