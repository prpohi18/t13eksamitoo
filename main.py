from flask import Flask, render_template, request

app = Flask(__name__)

# Pea python'i fail, mis haldab nuppude kaudu navigeerumist.
# Saadab muutuja HTML template'ile, mis sisaldab lehe nime.
@app.route('/', methods=['POST','GET'])
def index():
    if request.method == "POST":
        print("post")
        if request.form['submit'] == "currency":
            return render_template("currency.html", name="Valuuta")
        elif request.form['submit'] == "circle":
            return render_template("circle.html", name="Ringi pindala")
    return render_template("index.html")

# Käivitab app'i/serveri.
if __name__ == "__main__":
    app.run(debug=True)



