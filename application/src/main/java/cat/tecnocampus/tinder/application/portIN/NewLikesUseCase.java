package cat.tecnocampus.tinder.application.portIN;

import java.util.List;

public interface NewLikesUseCase {

    int newLikes(NewLikesCommand newLikesCommand);

    public class NewLikesCommand {
        private String origin;
        private List<String> targets;

        public NewLikesCommand() {
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public List<String> getTargets() {
            return targets;
        }

        public void setTargets(List<String> targets) {
            this.targets = targets;
        }
    }

}
