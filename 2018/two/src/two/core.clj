(ns two.core
  (:gen-class))

(defn part-one
  [input]
  (println "Part one")
  input)

(defn part-two
  [input]
  (println "Part two")
  input)

(defn -main
  [& args]
  (let [input (mapv read-string (clojure.string/split (slurp "resources/input.txt") #"\n"))]
    (part-one input)
    (part-two input)))
