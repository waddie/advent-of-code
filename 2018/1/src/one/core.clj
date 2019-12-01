(ns one.core
  (:gen-class))

(defn result-of-frequency-changes
  "Sum vector."
  [input]
  (apply + input))

(defn- found-first-number?
  [possibility already]
  (and (number? possibility)
       (nil? already)))

(defn first-duplicate-frequency-reached
  "Return the first frequency reached twice by applying frequency changes repeatedly."
  [input]
  (let [duplicate (atom nil)]
    (loop [journal [0]]
      (if (nil? @duplicate)
        (recur (loop [next-frequency        (first input)
                      remaining-frequencies (rest input)
                      journal               journal]
                 (let [frequency (+ (last journal) next-frequency)]
                   (when (found-first-number? (some #{frequency} journal)
                                              @duplicate)
                     (reset! duplicate frequency))
                   (if (and (seq remaining-frequencies)
                            (nil? @duplicate))
                     (recur (first remaining-frequencies)
                            (rest remaining-frequencies)
                            (conj journal frequency))
                     (conj journal frequency)))))
        @duplicate))))

(defn -main
  [& args]
  (let [input (mapv read-string (clojure.string/split (slurp "resources/input.txt") #"\n"))]
    (println "Part one")
    (println "Answer:" (str (result-of-frequency-changes input)))

    (println "Part two")
    (println "Answer:" (str (first-duplicate-frequency-reached input)))))
