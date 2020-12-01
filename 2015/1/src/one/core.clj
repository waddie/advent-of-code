(ns one.core
  (:gen-class))

(defn traverse-floors
  "Calculate final floor if ( is up and ) is down."
  [input]
  (let [freqs (frequencies (seq input))]
    (+ (or (get freqs \() 0)
       (* -1 (or (get freqs \)) 0)))))

(defn first-basement
  "Find the first step that leads to the basement."
  [input]
  (loop [index 0
         floor 0
         step (first (seq input))
         remaining (rest (seq input))]
    (if (== -1 floor)
      index
      (recur (inc index)
             (if (= \( step)
               (inc floor)
               (dec floor))
             (first remaining)
             (rest remaining)))))

(defn -main
  [& args]
  (let [input (slurp "resources/input.txt")]
    (println "Part one")
    (println "Answer:" (traverse-floors input))

    (println "Part two")
    (println "Answer:" (first-basement input))))
