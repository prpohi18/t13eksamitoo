from flask import Flask, render_template, request

app = Flask(__name__)

@app.route('/', methods=['POST','GET'])
def index():
    if request.method == "POST":
        print("post")
        if request.form['submit'] == "adding":
            return render_template("adding.html")
        elif request.form['submit'] == "currency":
            return render_template("currency.html")
        elif request.form['submit'] == "circle":
            return render_template("circle.html")
    return render_template("index.html")

@app.route('/calculator/<calc_type>')
def calculator(calc_type):
    return "Hello %s" % calc_type

if __name__ == "__main__":
    app.run(debug=True)



