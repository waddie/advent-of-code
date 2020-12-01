(ns four.core
  (:gen-class))

(defn- most-asleep
  "."
  []
  nil)

(defn- minute-most-asleep
  "."
  []
  nil)

(defn guard-and-minute
  "."
  [input]
  input)

(defn -main
  [& args]
  (let [input (sort (clojure.string/split (slurp "resources/input.txt")
                                          #"\n"))]
    (println "Part one")
    (println "Answer:" (guard-and-minute input))

    (println "Part two")))
