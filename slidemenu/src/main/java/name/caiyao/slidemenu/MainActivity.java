package name.caiyao.slidemenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import name.caiyao.slidemenu.view.SlidingMenu;

public class MainActivity extends AppCompatActivity {

    private SlidingMenu mLeftMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLeftMenu = (SlidingMenu) findViewById(R.id.sm);
    }

    public void toggleMenu(View view) {
        mLeftMenu.toggle();
    }
}
