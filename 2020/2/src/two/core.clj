(ns two.core
  (:gen-class)
  (:require [clojure.string :as string]
            [clojure.pprint :as pprint]
            [clojure.edn :as edn]))

(def input-file "resources/input.txt")

(defn process-passwords
  [v]
  (let [match-parts #"(\d+)-(\d+) ([a-z]): ([a-z]+)"]
    (mapv #(let [matcher (re-matcher match-parts %)
                 parts   (re-find matcher)]
             {:policy {:first (edn/read-string (get parts 1))
                       :second (edn/read-string (get parts 2))
                       :char (get parts 3)}
              :password (get parts 4)})
          v)))

(defn wrong-valid-password?
  [password policy]
  (let [valid-chars (count (filter #(= (:char policy) %)
                                   (string/split password #"")))]
    (and (>= valid-chars (:first policy))
         (<= valid-chars (:second policy)))))

(defn valid-password?
  [password policy]
  (let [chars        (string/split password #"")
        valid-char   (:char policy)
        first-char   (get chars (dec (:first policy)))
        second-char  (get chars (dec (:second policy)))
        first-match  (and (= valid-char
                             first-char)
                          (not= valid-char
                                second-char))
        second-match (and (not= valid-char
                                first-char)
                          (= valid-char
                             second-char))]
    (or first-match
        second-match)))

(defn valid-passwords
  [v validator]
  (filter #(validator (:password %)
                      (:policy %))
          v))

(defn -main
  [& args]
  (let [input     (string/split (slurp input-file) #"\n")
        passwords (process-passwords input)
        part-one  (valid-passwords passwords wrong-valid-password?)
        part-two  (valid-passwords passwords valid-password?)]
    (println "Part one")
    (println "Answer:" (count part-one) "\n")

    (println "Part two")
    (println "Answer:" (count part-two))))
