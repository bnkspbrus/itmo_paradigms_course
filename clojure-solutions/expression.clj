;(ns expression)

(defn variable [name]
  (fn [vals]
    (vals name)))

(defn constant [val]
  (constantly val))

(defn operation [f]
  (fn [& ops]
    (fn [vals]
      (apply f (map (fn [op] (op vals)) ops)))))

(defn double-div [a b] (/ a (double b)))

(def add (operation +))

(def subtract (operation -))

(def multiply (operation *))

(def divide (operation double-div))

(def negate (operation (fn [a] (- a))))

(def sin (operation (fn [a] (Math/sin a))))

(def cos (operation (fn [a] (Math/cos a))))

(def fun-arg #{+ - *})

(def dup-op                                                 ; operations that are duplicated with external
  {'sin sin
   'cos cos})

(defn parseFunction [expr]
  (let [tokens (read-string expr)]
    (letfn [(filter [token]
              (cond
                (list? token) (letfn [(action [f] (apply f (map filter (rest token))))]
                                (if (contains? dup-op (first token)) (action (dup-op (first token)))
                                  (let [fun (eval (first token))]
                                    (cond
                                      (= fun /) (action divide)
                                      (contains? fun-arg fun) (action (operation fun))
                                      :else (action fun)))))
                (symbol? token) (variable (str token))
                :else (constant token)))]
      (filter tokens))))