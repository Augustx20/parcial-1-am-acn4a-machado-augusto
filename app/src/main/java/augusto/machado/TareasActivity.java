package augusto.machado;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class TareasActivity extends AppCompatActivity {
    private LinearLayout layoutTareas;
    private FirebaseFirestore db;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas); // El layout corregido

        layoutTareas = findViewById(R.id.layoutTareas);
        db = FirebaseFirestore.getInstance();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        cargarTareas();
    }

    private void cargarTareas() {
        db.collection("users").document(uid).collection("tareas")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {

                        String titulo = doc.getString("titulo");
                        String fechaInicio = doc.getString("fechaInicio");
                        String fechaFin = doc.getString("fechaFin");
                        String raza = doc.getString("raza");
                        String anio = doc.getString("año");
                        String peso = doc.getString("peso");
                        String altura = doc.getString("altura");

                        // Contenedor para la tarjeta
                        LinearLayout card = new LinearLayout(this);
                        card.setOrientation(LinearLayout.VERTICAL);
                        card.setPadding(16, 16, 16, 16);
                        card.setBackgroundColor(Color.parseColor("#EEEEEE"));

                        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                        );
                        cardParams.setMargins(0, 0, 0, 24);
                        card.setLayoutParams(cardParams);
                        card.setBackgroundResource(R.drawable.rg_shape); // Borde opcional con drawable

                        // Texto
                        TextView texto = new TextView(this);
                        texto.setText(
                                "Título: " + titulo + "\n" +
                                        "Inicio: " + fechaInicio + "\n" +
                                        "Fin: " + fechaFin + "\n" +
                                        "Raza: " + raza + "\n" +
                                        "Años: " + anio + "\n" +
                                        "Peso: " + peso + "\n" +
                                        "Altura: " + altura
                        );
                        texto.setTextSize(16);

                        card.addView(texto);
                        layoutTareas.addView(card);
                    }

                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al cargar tareas", Toast.LENGTH_SHORT).show();
                });
    }


}

