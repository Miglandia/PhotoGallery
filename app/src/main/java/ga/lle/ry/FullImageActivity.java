package ga.lle.ry;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class FullImageActivity extends AppCompatActivity {
    ImageView full_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        full_image = findViewById(R.id.full_image);

        String data = getIntent().getExtras().getString("img");
        full_image.setImageURI(Uri.parse(data));
    }
}