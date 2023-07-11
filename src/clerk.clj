(ns clerk
  (:require [babashka.fs :as fs]
            [nextjournal.clerk :as clerk]
            [nrepl.cmdline]
            [nrepl.core]
            [nrepl.server :as nrepl]))

(defn serve! [opts]
  (fs/create-dirs "/tmp/sock")
  (let [nrepl-server (nrepl/start-server :bind "0.0.0.0" :port 6666)]
    (println (str "TCP nREPL started: '" (into {} nrepl-server) "'.")))


  (try
    (let [nrepl-server (nrepl/start-server :socket "/tmp/sock/nrepl.sock")]
      (println (str "Socket nREPL started: '" (into {} nrepl-server) "'.")))
    (catch Throwable e
      (println e)))


  (clerk/serve! opts))
