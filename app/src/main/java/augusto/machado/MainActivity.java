package augusto.machado;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }
    public void updateMessage(View view){
        Toast.makeText(this, "Nueva Tarea", Toast.LENGTH_LONG).show();
        LinearLayout title = findViewById(R.id.linearLayout);

        // Llama correctamente al método pasando el contexto y el layout padre
        newCard(this, title);
    }

    public void newCard(Context context, ViewGroup parentLayout) {
        FrameLayout card = new FrameLayout(context); // usa el contexto recibido

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        card.setLayoutParams(params);
        card.setPadding(40, 40, 40, 40);

        // Asegúrate de que el drawable rg_shape existe en res/drawable/
        Drawable fondo = ContextCompat.getDrawable(context, R.drawable.rg_shape);
        card.setBackground(fondo);

        TextView texto = new TextView(context);
        texto.setText("Soy un texto");
        card.addView(texto);

        parentLayout.addView(card);
    }


}