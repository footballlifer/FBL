package nanofuntas.fbl;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PlayerRatingActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_rating);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_player_rating, menu);
        return true;
    }
}
