(ns two.core
  (:gen-class))

(defn- input-values
  "Set the input values"
  [input noun verb]
  (-> input
      (assoc 1 noun)
      (assoc 2 verb)))

(defn- arithmetic
  "Execute arithmetic opcode."
  [f program position]
  (assoc program
         (nth program (+ 3 position))
         (f (nth program (nth program (+ 1 position)))
            (nth program (nth program (+ 2 position))))))

(defn process-opcodes
  "Run program."
  [input]
  (loop [program input
         position 0]
    (condp == (nth program position)
      1 (recur (arithmetic + program position)
               (+ 4 position))
      2 (recur (arithmetic * program position)
               (+ 4 position))
      99 (nth program 0))))

(defn search-for-inputs
  "Find the input values that, given an IntCode program, results in the target number."
  [input target]
  (first (filter #(== target (:result %))
                 (map (fn [values]
                        (conj values
                              {:result (process-opcodes (input-values input
                                                                      (:noun values)
                                                                      (:verb values)))}))
                      (for [noun (range 100)
                            verb (range 100)]
                        {:noun noun
                         :verb verb})))))

(defn -main
  [& args]
  (let [input (mapv read-string (clojure.string/split
                                 (slurp "resources/input.txt")
                                 #","))]
    (println "Part one")
    (println "Answer:" (process-opcodes (input-values input 12 2)))

    (println "Part two")
    (let [{noun :noun
           verb :verb} (search-for-inputs input 19690720)]
      (println "Answer:" (+ (* 100 noun) verb)))))
