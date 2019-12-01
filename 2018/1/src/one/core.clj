(ns one.core
  (:gen-class))

(defn result-of-frequency-changes
  "Sum vector."
  [input]
  (apply + input))

(defn first-duplicate-frequency-reached
  "Return the first frequency reached twice by applying frequency changes repeatedly."
  [input]
  (loop [cycle {:journal   #{0}
                :frequency 0
                :duplicate nil}]
    (if (nil? (:duplicate cycle))
      (recur (loop [next-frequency        (first input)
                    remaining-frequencies (rest input)
                    journal               (:journal cycle)
                    current-frequency     (:frequency cycle)]
               (let [frequency (+ current-frequency next-frequency)
                     duplicate (if (contains? journal frequency)
                                 frequency
                                 nil)]
                 (if (and (seq remaining-frequencies)
                          (nil? duplicate))
                   (recur (first remaining-frequencies)
                          (rest remaining-frequencies)
                          (conj journal frequency)
                          frequency)
                   {:journal   (conj journal frequency)
                    :frequency frequency
                    :duplicate duplicate}))))
      (:duplicate cycle))))

(defn -main
  [& args]
  (let [input (mapv read-string (clojure.string/split (slurp "resources/input.txt") #"\n"))]
    (println "Part one")
    (println "Answer:" (str (result-of-frequency-changes input)))

    (println "Part two")
    (println "Answer:" (str (first-duplicate-frequency-reached input)))))
