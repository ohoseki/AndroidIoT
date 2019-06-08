package br.com.rafaelohoseki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_temperatura.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener

class TemperaturaActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var tempRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperatura)

        txtTemperatura.text = "calculando"

        database = FirebaseDatabase.getInstance()
        tempRef = database.getReference("temp01")

        tempRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(Int::class.java) as Int

                txtTemperatura.text = "A Temperatura e " + value.toString()+ " graus Celsius";

                // verde
                if (value < 23) {
                    botaoTemperatura.displayedChild = 0;
                    // amarelo
                } else if (value >= 23 && value < 24) {
                    botaoTemperatura.displayedChild = 1;
                }
                // vermelho
                else
                {
                    botaoTemperatura.displayedChild = 2;
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value

            }
        })
    }
}

