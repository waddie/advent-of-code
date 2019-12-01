(ns one.core
  (:gen-class))

(defn result-of-frequency-changes
  "Sum vector."
  [input]
  (apply + input))

(defn first-duplicate-frequency-reached
  "Return the first frequency reached twice by applying frequency changes repeatedly."
  [input]
  (let [log (atom {:journal [0]
                   :duplicate nil})]
    (while (nil? (:duplicate @log))
      (doall (map
              (fn [x]
                (let [y (+ (last (:journal @log)) x)]
                  (when (and (nil? (:duplicate @log))
                             (number? (some #{y} (:journal @log))))
                    (swap! log #(assoc % :duplicate y)))
                  (swap! log #(assoc % :journal (conj (:journal %) y)))))
              input)))
    (:duplicate @log)))

; (defn first-duplicate-frequency-reached2
;   "Return the first frequency reached twice by applying frequency changes repeatedly."
;   [input]

;   (loop [frequencies]))

(defn -main
  [& args]
  (let [input (mapv read-string (clojure.string/split (slurp "resources/input.txt") #"\n"))]
    (println "Part one")
    (println "Answer:" (str (result-of-frequency-changes input)))

    (println "Part two")
    (println "Answer:" (str (first-duplicate-frequency-reached input)))))
