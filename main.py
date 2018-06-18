from flask import Flask, render_template, request

import sql
import sqlite3
#conn = sqlite3.connect('database.db')
#conn.execute('CREATE TABLE history (calc_name TEXT, timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)')
#print("Table created successfully")
#conn.close()

app = Flask(__name__)

# Pea python'i fail, mis haldab nuppude kaudu navigeerumist.
# Saadab muutuja HTML template'ile, mis sisaldab lehe nime.
@app.route('/', methods=['POST','GET'])
def index():
    if request.method == "POST":
        print("post")
        if request.form['submit'] == "currency":
            conn = sqlite3.connect('database.db')
            conn.execute("INSERT INTO history (calc_name) \
                  VALUES ('Valuuta')")
            conn.commit()
            print("Records created successfully")
            conn.close()
            return render_template("currency.html", name="Valuuta")
        elif request.form['submit'] == "circle":
            conn = sqlite3.connect('database.db')
            conn.execute("INSERT INTO history (calc_name) \
                              VALUES ('Ringi pindala')")
            conn.commit()
            conn.close()
            return render_template("circle.html", name="Ringi pindala")
    return render_template("index.html")


@app.route('/list')
def list():
    conn = sqlite3.connect('database.db')
    cursor = conn.execute("SELECT calc_name, timestamp from history")
    print(cursor)
    return render_template("list.html", rows=cursor)

# Käivitab app'i/serveri.
if __name__ == "__main__":
    app.run(debug=True)



